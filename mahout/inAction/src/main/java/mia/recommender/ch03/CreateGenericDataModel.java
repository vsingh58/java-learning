package mia.recommender.ch03;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;

class CreateGenericDataModel {

  private CreateGenericDataModel() {
  }

  public static void main(String[] args) {
    FastByIDMap<PreferenceArray> preferences =
      new FastByIDMap<PreferenceArray>();
    PreferenceArray prefsForUser1 = new GenericUserPreferenceArray(10);
    prefsForUser1.setUserID(0, 1L);
    prefsForUser1.setItemID(0, 101L);
    prefsForUser1.setValue(0, 3.0f);
    prefsForUser1.setItemID(1, 102L);
    prefsForUser1.setValue(1, 4.5f);

    preferences.put(1L, prefsForUser1);

    System.out.println(preferences.size() + ":");

    PreferenceArray array = preferences.get(1l);
    print(  array.length());
      print(array.getIDs());
      print(array);

      System.gc();
      Runtime.getRuntime().freeMemory();
      Runtime.getRuntime().totalMemory();

    DataModel model = new GenericDataModel(preferences);

    System.out.println(model);
  }

    public static final void print(Object obj){
        System.out.println(obj);
    }


}
