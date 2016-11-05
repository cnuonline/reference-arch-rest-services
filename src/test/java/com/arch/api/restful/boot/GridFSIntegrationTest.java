package com.arch.api.restful.boot;

/**
 * Created by SRINIVASULU on 05/11/16.
 */


        import static org.hamcrest.CoreMatchers.is;
        import static org.hamcrest.Matchers.nullValue;
        import static org.junit.Assert.assertEquals;
        import static org.junit.Assert.assertNotNull;
        import static org.junit.Assert.assertNull;
        import static org.junit.Assert.assertThat;

        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.io.InputStream;
        import java.util.List;


        import com.arch.api.restful.config.MongoConfig;
        import org.junit.After;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.data.mongodb.core.MongoOperations;
        import org.springframework.data.mongodb.core.query.Criteria;
        import org.springframework.data.mongodb.core.query.Query;
        import org.springframework.data.mongodb.gridfs.GridFsResource;
        import org.springframework.data.mongodb.gridfs.GridFsTemplate;
        import org.springframework.test.context.ContextConfiguration;
        import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

        import com.mongodb.BasicDBObject;
        import com.mongodb.DBObject;
        import com.mongodb.gridfs.GridFSDBFile;
        import org.springframework.test.context.support.AnnotationConfigContextLoader;


@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan({"com.arch.api.restful.boot","com.jcabi","com.arch.api.restful.config"})
@ContextConfiguration(classes=MongoConfig.class, loader=AnnotationConfigContextLoader.class)
public class GridFSIntegrationTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MongoConfig mongoConfig;

    @After
    public void tearDown() {
        List<GridFSDBFile> fileList = null;
        try {
            fileList = mongoConfig.gridFsTemplate().find(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (GridFSDBFile file : fileList) {
            try {
                mongoConfig.gridFsTemplate().delete(new Query(Criteria.where("filename").is(file.getFilename())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void whenStoringFileWithMetadata_thenFileAndMetadataAreStored() throws Exception {
        DBObject metaData = new BasicDBObject();
        metaData.put("user", "alex");
        InputStream inputStream = null;
        String id = "";
        try {
            inputStream = new FileInputStream("src/main/resources/test.png");
            id = mongoConfig.gridFsTemplate().store(inputStream, "test.png", "image/png", metaData).getId().toString();
        } catch (FileNotFoundException ex) {
            logger.error("File not found", ex);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    logger.error("Failed to close", ex);
                }
            }
        }

        assertNotNull(id);
    }

    @Test
    public void givenFileWithMetadataExist_whenFindingFileById_thenFileWithMetadataIsFound() throws Exception {
        DBObject metaData = new BasicDBObject();
        metaData.put("user", "alex");
        InputStream inputStream = null;
        String id = "";
        try {
            inputStream = new FileInputStream("src/main/resources/test.png");
            id = mongoConfig.gridFsTemplate().store(inputStream, "test.png", "image/png", metaData).getId().toString();
        } catch (FileNotFoundException ex) {
            logger.error("File not found", ex);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    logger.error("Failed to close", ex);
                }
            }
        }

        GridFSDBFile gridFSDBFile = mongoConfig.gridFsTemplate().findOne(new Query(Criteria.where("_id").is(id)));

        assertNotNull(gridFSDBFile);
        assertNotNull(gridFSDBFile.getInputStream());
        assertThat(gridFSDBFile.numChunks(), is(1));
        assertThat(gridFSDBFile.containsField("filename"), is(true));
        assertThat(gridFSDBFile.get("filename"), is("test.png"));
        assertThat(gridFSDBFile.getId().toString(), is(id));
        assertThat(gridFSDBFile.keySet().size(), is(9));
        assertNotNull(gridFSDBFile.getMD5());
        assertNotNull(gridFSDBFile.getUploadDate());
        assertNull(gridFSDBFile.getAliases());
        assertNotNull(gridFSDBFile.getChunkSize());
        assertThat(gridFSDBFile.getContentType(), is("image/png"));
        assertThat(gridFSDBFile.getFilename(), is("test.png"));
        assertThat(gridFSDBFile.getMetaData().get("user"), is("alex"));
    }

    @Test
    public void givenMetadataAndFilesExist_whenFindingAllFiles_thenFilesWithMetadataAreFound() throws Exception {
        DBObject metaDataUser1 = new BasicDBObject();
        metaDataUser1.put("user", "alex");
        DBObject metaDataUser2 = new BasicDBObject();
        metaDataUser2.put("user", "david");
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream("src/main/resources/test.png");
            mongoConfig.gridFsTemplate().store(inputStream, "test.png", "image/png", metaDataUser1);
            mongoConfig.gridFsTemplate().store(inputStream, "test.png", "image/png", metaDataUser2);
        } catch (FileNotFoundException ex) {
            logger.error("File not found", ex);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    logger.error("Failed to close", ex);
                }
            }
        }

        List<GridFSDBFile> gridFSDBFiles = mongoConfig.gridFsTemplate().find(null);

        assertNotNull(gridFSDBFiles);
        assertThat(gridFSDBFiles.size(), is(2));
    }

    @Test
    public void givenMetadataAndFilesExist_whenFindingAllFilesOnQuery_thenFilesWithMetadataAreFoundOnQuery() throws Exception {
        DBObject metaDataUser1 = new BasicDBObject();
        metaDataUser1.put("user", "alex");
        DBObject metaDataUser2 = new BasicDBObject();
        metaDataUser2.put("user", "david");
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream("src/main/resources/test.png");
            mongoConfig.gridFsTemplate().store(inputStream, "test.png", "image/png", metaDataUser1);
            mongoConfig.gridFsTemplate().store(inputStream, "test.png", "image/png", metaDataUser2);
        } catch (FileNotFoundException ex) {
            logger.error("File not found", ex);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    logger.error("Failed to close", ex);
                }
            }
        }

        List<GridFSDBFile> gridFSDBFiles = mongoConfig.gridFsTemplate().find(new Query(Criteria.where("metadata.user").is("alex")));

        assertNotNull(gridFSDBFiles);
        assertThat(gridFSDBFiles.size(), is(1));
    }

    @Test
    public void givenFileWithMetadataExist_whenDeletingFileById_thenFileWithMetadataIsDeleted() throws Exception {
        DBObject metaData = new BasicDBObject();
        metaData.put("user", "alex");
        InputStream inputStream = null;
        String id = "";
        try {
            inputStream = new FileInputStream("src/main/resources/test.png");
            id = mongoConfig.gridFsTemplate().store(inputStream, "test.png", "image/png", metaData).getId().toString();
        } catch (FileNotFoundException ex) {
            logger.error("File not found", ex);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    logger.error("Failed to close", ex);
                }
            }
        }

        mongoConfig.gridFsTemplate().delete(new Query(Criteria.where("_id").is(id)));

        assertThat(mongoConfig.gridFsTemplate().findOne(new Query(Criteria.where("_id").is(id))), is(nullValue()));
    }

    @Test
    public void givenFileWithMetadataExist_whenGettingFileByResource_thenFileWithMetadataIsGotten() throws Exception {
        DBObject metaData = new BasicDBObject();
        metaData.put("user", "alex");
        InputStream inputStream = null;
        String id = "";
        try {
            inputStream = new FileInputStream("src/main/resources/test.png");
            id = mongoConfig.gridFsTemplate().store(inputStream, "test.png", "image/png", metaData).getId().toString();
        } catch (FileNotFoundException ex) {
            logger.error("File not found", ex);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    logger.error("Failed to close", ex);
                }
            }
        }

        GridFsResource[] gridFsResource = mongoConfig.gridFsTemplate().getResources("test*");

        assertNotNull(gridFsResource);
        assertEquals(gridFsResource.length, 1);
        assertThat(gridFsResource[0].getFilename(), is("test.png"));
    }
}