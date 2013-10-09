package org.elasticsearch.river.twitter.test;

import java.io.IOException;

import com.chimpler.example.bayes.Classifier;

public class ClassifyTest {
	public static void main(String[] args) throws IOException {
		String message = "this is a bad message";
		System.out.println(Classifier.classify("/tmp/local/model","/tmp/local/labelindex","/tmp/local/dictionary.file-0","/tmp/local/df-count",message));
	}
}
