package org.chaos.cp.connector;

import org.apache.commons.lang3.StringUtils;
import org.chaos.cp.entity.Song;
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
                    Song song = new Song(path.toString());
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


}