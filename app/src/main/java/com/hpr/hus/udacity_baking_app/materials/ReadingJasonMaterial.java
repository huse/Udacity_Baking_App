package com.hpr.hus.udacity_baking_app.materials;

/**
 * Created by hk640d on 12/7/2017.
 */
import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.util.JsonReader;
import android.widget.Toast;

import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceInputStream;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.util.Util;
import com.hpr.hus.udacity_baking_app.R;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadingJasonMaterial {

    private String mVideoURLstr;
    private String mThumbnailURLstr;
    private int mServingint;
    private int mRecipeIDs;

    private int mQuantityInt;
    private String mMeasurement;
    private String mIngredients;
    private JSONArray mJArraySteps;
    private int mStepIDint;
    private String mShortDescriptions;
    private String mDescriptions;
    private String mRecipeNames;
    private JSONArray mJArrayIngredients;
    private String mImages;

private ReadingJasonMaterial(int recipeID,String recipeName,JSONArray jArrayIngredients,int quantity,
                             String measure, String ingredient, JSONArray jArraySteps, int stepID, String shortDescription ,String description
                             , String videoURL, String thumbnailURL, int serving, String image ){

    mRecipeIDs = recipeID;
    mRecipeNames = recipeName;
    mJArrayIngredients = jArrayIngredients;
    mQuantityInt = quantity;
    mMeasurement = measure;
    mIngredients = ingredient;
    mJArraySteps = jArraySteps;
    mStepIDint = stepID;
    mShortDescriptions = shortDescription;
    mDescriptions = description;
    mVideoURLstr = videoURL;
    mThumbnailURLstr = thumbnailURL;
    mServingint = serving;
    mImages = image;

}
    private static JsonReader readJSONFile(Context context) throws IOException {
        AssetManager assetManager = context.getAssets();
        String uri = null;

        try {
            for (String asset : assetManager.list("")) {
                if (asset.endsWith(".baking_recipes.json")) {
                    uri = "materials:///" + asset;
                }
            }
        } catch (IOException e) {
            Toast.makeText(context, R.string.recipe_list_load_error, Toast.LENGTH_LONG)
                    .show();
        }

        String userAgent = Util.getUserAgent(context, "ClassicalMusicQuiz");
        DataSource dataSource = new DefaultDataSource(context, null, userAgent, false);
        DataSpec dataSpec = new DataSpec(Uri.parse(uri));
        InputStream inputStream = new DataSourceInputStream(dataSource, dataSpec);

        JsonReader reader = null;
        try {
            reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        } finally {
            Util.closeQuietly(dataSource);
        }

        return reader;
    }
   /* private static ReadingJasonMaterial readEntry(JsonReader reader) {
        Integer id = -1;
        String recipeName = null;
        String title = null;
        String uri = null;
        String albumArtID = null;

        int  quantity;
        String measure;

        String description;
        String videoURL;
        String composer = null;
        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                switch (name) {
                    case "name":
                        title = reader.nextString();
                        break;
                    case "id":
                        id = reader.nextInt();
                        break;
                    case "composer":
                        composer = reader.nextString();
                        break;
                    case "uri":
                        uri = reader.nextString();
                        break;
                    case "albumArtID":
                        albumArtID = reader.nextString();
                        break;
                    default:
                        break;
                }
            }
            reader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ReadingJasonMaterial(id, composer, title, uri, albumArtID);
    }*/
}
