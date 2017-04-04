package com.baselogic.tutorials.reference.nio;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by mickknutson on 4/23/15.
 */
@RunWith(JUnit4.class)
public class NIOExamplesTests {
    private static final Logger logger = LoggerFactory.getLogger(NIOExamplesTests.class);

    public static final String RESOURCES_HOME = "src/main/resources/";
    public static final String TEST_RESOURCES_HOME = "src/test/resources/";


    //-----------------------------------------------------------------------//
    // Lifecycle Methods
    //-----------------------------------------------------------------------//
    @Before
    public void beforeEachTest() throws Exception {
    }

    @After
    public void afterEachTest() throws Exception {
    }

    //-----------------------------------------------------------------------//
    // Unit Tests
    //-----------------------------------------------------------------------//
    @Test
    public void nioReadTest() throws Exception {

        String fileName = TEST_RESOURCES_HOME + "com/baselogic/tutorials/reference/nio/access_log";
        File file = new File(fileName);
        logger.info("----->>>>> raf: {}", file.getAbsolutePath());

        RandomAccessFile raf = new RandomAccessFile(file, "rw");

        try {

            FileChannel fc = raf.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);

            byte[] bytes = null;
            int count = -1;

            while ((count = fc.read(buf)) != -1) {
                bytes = new byte[count];

                // after Reading, flip buffer to prepare for writes
                buf.flip();
                buf.get(bytes);
                for (byte b : bytes) {
                    logger.debug("{}", (char) b);
                }
                buf.clear();
            }

            // write to file
            buf.put("Hello".getBytes());

            // after Reading, flip buffer to prepare for writes
            buf.flip();
            fc.write(buf);

            logger.info("Wrote string \"Hello\" to access_log.");
            fc.close();
        } catch (Exception e) {
            e.printStackTrace();

            logger.info("----->>>>> raf: {}", file.getAbsolutePath());
        }

    }

}
