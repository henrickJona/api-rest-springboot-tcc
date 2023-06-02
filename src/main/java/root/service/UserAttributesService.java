package root.service;

import root.model.UserAttributes;
import root.model.Response;
import root.repository.UserAttributesRepository;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;

import org.springframework.asm.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
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
    DataSource source = new DataSource("C:/Users/Jonathan/Downloads/tcc/tcc/target/classes/dataset_smotted.arff");
   
 
        Instance inst = new DenseInstance(10);
        inst.setDataset(source.getDataSet(9));
        inst.setValue(0, userAttributes.getAge());
        inst.setValue(1, userAttributes.getMenopause());
        inst.setValue(2, userAttributes.getTumor_size());
        inst.setValue(3, userAttributes.getInv_nodes());
        inst.setValue(4, userAttributes.getNode_caps());
        inst.setValue(5, userAttributes.getDeg_malig());
        inst.setValue(6, userAttributes.getBreast());
        inst.setValue(7, userAttributes.getBreat_quad());
        inst.setValue(8, userAttributes.getIrradiat());
        inst.setValue(9, userAttributes.getClassResult());
       

        RandomForest  rf =(RandomForest) SerializationHelper.read("C:/Users/Jonathan/Downloads/tcc/tcc/target/classes/tcc_model.model");
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

   
}
