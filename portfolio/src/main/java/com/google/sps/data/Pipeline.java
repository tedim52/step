package com.google.sps.data;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.util.Properties;

/* Returns a stanford NLP class with necessary properties for sentiment analysis */
public class Pipeline {

    private static Properties properties;
    private static String propertiesName="tokenize, ssplit, pos, parse, sentiment";
    private static StanfordCoreNLP stanfordCoreNLP;

    private Pipeline() { }

    static {
        properties = new Properties();
        properties.setProperty("annotators", propertiesName);
    }

    public static StanfordCoreNLP getPipeline() {
        if(stanfordCoreNLP == null) {
            stanfordCoreNLP = new StanfordCoreNLP(properties);
            System.out.println("Creating new stanford core nlp");
        }
        System.out.println("Returning existing stanford core nlp");
        return stanfordCoreNLP;
    }

}