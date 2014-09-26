package mia.recommender.ch04;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.example.grouplens.GroupLensDataModel;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;

/*
* code - 4.3 sample
* */
final class GroupLens10MEvalIntro {

    private GroupLens10MEvalIntro() {
    }

    public static void main(String[] args) throws Exception {
        DataModel model = new GroupLensDataModel(new File("src/main/java/mia/recommender/ch04/ratings.dat"));

        RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
        RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
            @Override
            public Recommender buildRecommender(DataModel model) throws TasteException {
//                UserSimilarity similarity = new EuclideanDistanceSimilarity(model); // 0.8119024633192099
                UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
//                UserSimilarity similarity = new PearsonCorrelationSimilarity(model, Weighting.WEIGHTED); //0.764597
                UserNeighborhood neighborhood =
//                        new ThresholdUserNeighborhood(0.7, similarity, model);//0.9316
                        new NearestNUserNeighborhood(100, similarity, model); // 0.786496
                return new GenericUserBasedRecommender(model, neighborhood, similarity);
            }
        };
        double score = evaluator.evaluate(recommenderBuilder, null, model, 0.95, 0.05);
        System.out.println(score);
    }

}
