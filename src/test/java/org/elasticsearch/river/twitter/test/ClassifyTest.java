package org.elasticsearch.river.twitter.test;

import java.io.IOException;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.mahout.classifier.naivebayes.BayesUtils;
import org.apache.mahout.classifier.naivebayes.NaiveBayesModel;
import org.apache.mahout.classifier.naivebayes.StandardNaiveBayesClassifier;

import com.chimpler.example.bayes.Classifier;

public class ClassifyTest {
	public static void main(String[] args) throws IOException {

		String message = "this is a bad message";
		
		String modelPath = "/tmp/local/model";
		String labelIndexPath = "/tmp/local/labelindex";
		String dictionaryPath = "/tmp/local/dictionary.file-0";
		String documentFrequencyPath = "/tmp/local/df-count";
		
		Configuration configuration = new Configuration();

		// model is a matrix (wordId, labelId) => probability score
		NaiveBayesModel model = NaiveBayesModel.materialize(new Path(modelPath), configuration);
		
		StandardNaiveBayesClassifier classifier = new StandardNaiveBayesClassifier(model);

		// labels is a map label => classId
		Map<Integer, String> labels = BayesUtils.readLabelIndex(configuration, new Path(labelIndexPath));
		Map<String, Integer> dictionary = Classifier.readDictionnary(configuration, new Path(dictionaryPath));
		Map<Integer, Long> documentFrequency = Classifier.readDocumentFrequency(configuration, new Path(documentFrequencyPath));
		
		System.out.println(Classifier.classify(classifier, labels, dictionary, documentFrequency, message));
	}
}
