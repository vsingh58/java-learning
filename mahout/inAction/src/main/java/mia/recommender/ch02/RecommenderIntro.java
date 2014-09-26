package mia.recommender.ch02;

import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;
import java.io.*;
import java.util.*;

class RecommenderIntro {

  private RecommenderIntro() {
  }

  public static void main(String[] args) throws Exception {

    DataModel model = new FileDataModel(new File("src/main/java/mia/recommender/ch02/intro.csv"));

//    UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
    UserSimilarity similarity = new PearsonCorrelationSimilarity(model, Weighting.WEIGHTED);
     System.out.println( similarity.userSimilarity(1, 5));

    UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

    Recommender recommender = new GenericUserBasedRecommender(
        model, neighborhood, similarity);

    List<RecommendedItem> recommendations =
        recommender.recommend(1, 1);

    for (RecommendedItem recommendation : recommendations) {
      System.out.println(recommendation); //RecommendedItem[item:104, value:4.257081]
    }
  }

}
