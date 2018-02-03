package org.chaos.cp.connector;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.apache.commons.lang3.StringUtils;
import org.chaos.cp.entity.Artist;
import org.chaos.cp.entity.Song;
import org.chaos.cp.repository.ArtistRepository;
import org.chaos.cp.repository.SongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@EnableScheduling
@Component
public class LocalFilesystemMusicFetcher {
    private static final Logger LOG = LoggerFactory.getLogger(LocalFilesystemMusicFetcher.class);
    private static final String DIRECTORY = "C:\\"; //XXX configure me

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Scheduled(fixedRate = 30*60*1000)
    public void fetchMusic() throws IOException {
        LOG.info("fetching music from local filesystem " + DIRECTORY);
        Collection<Song> songs = walkFileTree();
        LOG.info("found {} songs", songs.size());
        songRepository.save(songs);
    }

    private Collection<Song> walkFileTree() throws IOException {
        final List<Song> songs = new LinkedList<>();
        Files.walkFileTree(Paths.get(DIRECTORY), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes)
                    throws IOException {
                FileVisitResult result = super.visitFile(path, basicFileAttributes);
                if (StringUtils.startsWith(Files.probeContentType(path), "audio")) {
                    LOG.debug("found " + path.toString());
                    Song song = parseSongDetails(path);
                    songs.add(song);
                }
                return result;
            }

            @Override
            public FileVisitResult visitFileFailed(Path path, IOException e) {
                return FileVisitResult.SKIP_SUBTREE;
            }
        });
        return songs;
    }

    private Song parseSongDetails(Path path) throws IOException {
        Song song = null;
        try {
            Mp3File mp3file = new Mp3File(path.toString());
            String artistName = null;
            String title = null;
            if (mp3file.hasId3v2Tag()) {
                artistName = mp3file.getId3v2Tag().getAlbumArtist();
                title = mp3file.getId3v2Tag().getTitle();
            } else if (mp3file.hasId3v1Tag()) {
                artistName = mp3file.getId3v1Tag().getArtist();
                title = mp3file.getId3v1Tag().getTitle();
            }
            if (StringUtils.isNotBlank(title)) {
                song = new Song(title);
                Artist artist = artistRepository.findArtistByName(artistName);
                if (artist == null) {
                    artist = new Artist();
                    artist.setName(artistName);
                    artist = artistRepository.save(artist);
                }
                song.setArtist(artist);
            }
        } catch (UnsupportedTagException e) {
            LOG.warn("{} has unsupported tag", path.toString(), e);
        } catch (InvalidDataException e) {
            LOG.debug("{} is probably not a mp3 file", path.toString(), e);
        } catch (IllegalArgumentException e) {
            LOG.warn("{} has inconsistent tag data", path.toString(), e);
        }
        if (song == null) {
            song = new Song(StringUtils.substringBeforeLast(path.getFileName().toString(), ".")); //XXX split filename by -
        }
        song.setRef(path.toString());
        return song;
    }


}