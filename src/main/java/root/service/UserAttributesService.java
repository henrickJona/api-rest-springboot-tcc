package root.service;

import root.model.UserAttributes;
import root.model.Response;
import root.repository.UserAttributesRepository;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;

import org.springframework.asm.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.ClassPathResource;
import java.io.InputStream;
import java.net.URL;
import org.springframework.stereotype.Service;
import weka.core.*;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.converters.ConverterUtils.DataSource;

@Service
public class UserAttributesService {
    @Autowired
    private UserAttributesRepository repository;
    @Autowired
    private ResourceLoader resourceLoader;

    public Response predict(UserAttributes userAttributes) throws Exception {
        ClassPathResource resource = new ClassPathResource("dataset_smotted.arff");
        String inputStream = resource.getPath().toString();
        ClassLoader classLoader = getClass().getClassLoader();
        URL resourcex = classLoader.getResource("tcc_model.model");
System.out.println(this.getFileFromResourceAsStream("tcc_model.model")+" "+inputStream);
        //Resource resource=resourceLoader.getResource("classpath:/dataset_smotted.arff");
        //Resource resourceModel=resourceLoader.getResource("classpath:/tcc_model.model");
        //System.out.println(inputStream+"k"+resource.contentLength());
        //tafuncionadDataSource source = new DataSource("./dataset_smotted.arff");
        DataSource source = new DataSource(inputStream);
    //DataSource source = new DataSource(resource.getURI());
   System.out.println(userAttributes);System.out.println("test1");
        Instance inst = new DenseInstance(10);
        System.out.println("test2");
        
        inst.setDataset(source.getDataSet(9));
        System.out.println(userAttributes.getInv_nodes()+"Ooooo"+userAttributes.getIrradiat()+userAttributes.getBreast()+"test3"+inst+userAttributes.getAge()+userAttributes.getMenopause()+userAttributes.getTumor_size()+userAttributes.getInv_nodes()+userAttributes.getClassResult()+userAttributes.getBreast_quad());
        inst.setValue(0, userAttributes.getAge());
        System.out.println(1);
        inst.setValue(1, userAttributes.getMenopause());
        System.out.println(2);
        inst.setValue(2, userAttributes.getTumor_size());
        System.out.println(3);
        inst.setValue(3, userAttributes.getInv_nodes());
        System.out.println(4);
        inst.setValue(4, userAttributes.getNode_caps());
        System.out.println(5);
        inst.setValue(5, userAttributes.getDeg_malig());
        System.out.println(6);
        inst.setValue(6, userAttributes.getBreast());
        System.out.println(7);
        inst.setValue(7, userAttributes.getBreast_quad());
        System.out.println(8);
        inst.setValue(8, userAttributes.getIrradiat());
        System.out.println(9);
        inst.setValue(9, userAttributes.getClassResult());
        System.out.println(4);
        System.out.println("test2");
        //RandomForest  rf =(RandomForest) SerializationHelper.read("C:/Users/Jonathan/Downloads/tcc/tcc/target/classes/tcc_model.model");
//System.out.println(inst+"lp");
System.out.println("ju");
        RandomForest  rf =(RandomForest) SerializationHelper.read(this.getFileFromResourceAsStream("tcc_model.model"));
        System.out.println(rf+"ALon");
        rf.buildClassifier(source.getDataSet(9));
       Enumeration classes = source.getDataSet(9).classAttribute().enumerateValues();
       while (classes.hasMoreElements()){
            System.out.println(classes.nextElement());
       }
        Evaluation eTest = new Evaluation(source.getDataSet(9));
        eTest.crossValidateModel(rf,source.getDataSet(9),10,new Random());
        System.out.println(eTest.getMetricsToDisplay());
        System.out.println(eTest.correct());
        System.out.println(eTest.incorrect());
        System.out.println(eTest.precision(1));
        System.out.println("Probabilidade de não ter recorrência: "+rf.distributionForInstance(inst)[0]);
        double pNR = rf.distributionForInstance(inst)[0];
        double pR = rf.distributionForInstance(inst)[1];
        double pI = eTest.precision(1);
        double pC = eTest.precision(0);
        Response response = new Response(pNR,pR,pI,pC);
       
        System.out.println(response);
        System.out.println("Probabilidade de ter recorrência: "+rf.distributionForInstance(inst)[1]);
        return response;
    }
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
   
}
