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
    private ResourceLoader resourceLoader;

    public Response predict(UserAttributes userAttributes) throws Exception {
        // Carregar dataset corretamente
        Resource resource = resourceLoader.getResource("classpath:/dataset_smotted.arff");
        DataSource source = new DataSource(resource.getInputStream());
        Instances dataset = source.getDataSet();
        dataset.setClassIndex(dataset.numAttributes() - 1); // Define a última coluna como classe

        // Criar nova instância para predição
        Instance inst = new DenseInstance(dataset.numAttributes());
        inst.setDataset(dataset);

        // Definir valores (sem a classe)
        inst.setValue(0, userAttributes.getAge());
        inst.setValue(1, userAttributes.getMenopause());
        inst.setValue(2, userAttributes.getTumor_size());
        inst.setValue(3, userAttributes.getInv_nodes());
        inst.setValue(4, userAttributes.getNode_caps());
        inst.setValue(5, userAttributes.getDeg_malig());
        inst.setValue(6, userAttributes.getBreast());
        inst.setValue(7, userAttributes.getBreast_quad());
        inst.setValue(8, userAttributes.getIrradiat());

        // Carregar modelo treinado
        RandomForest rf = (RandomForest) SerializationHelper.read("target/classes/tcc_model.model");

        // Fazer a predição
        double[] probabilities = rf.distributionForInstance(inst);
        double pNR = probabilities[0]; // Probabilidade de não ter recorrência
        double pR = probabilities[1]; // Probabilidade de ter recorrência

        Response response = new Response(pNR, pR, 0, 0); // Remove métricas desnecessárias
        return response;
    }
}
