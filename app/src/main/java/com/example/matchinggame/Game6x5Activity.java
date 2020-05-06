package com.example.matchinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;


import com.squareup.picasso.Picasso;

public class Game6x5Activity extends AppCompatActivity implements TaskCompleted {

    //Variables
    String URL_STRING = "https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
    ArrayList<String> img_urls;
    final int NUM_IMGS = 15;

    AlertDialog.Builder builder;
    ArrayList<ImageButton> buttons;
    Map<ImageButton, Card> hm;
    Set<ImageButton> matchedCards;

    boolean isBoardLocked;
    ImageButton firstCard;
    ImageButton secondCard;

    TextView flips;
    TextView matches;
    int flip_count = 0;
    int match_count = 0;
    Button shuffle;

    //initializes data structures and makes API call
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6x5);

        builder = new AlertDialog.Builder(this);

        flips = findViewById(R.id.textView1);
        matches = findViewById(R.id.textView2);

        flips.setText("Flips: " + Integer.toString(flip_count));
        matches.setText("Matches: " + Integer.toString(match_count));

        img_urls = new ArrayList<>();

        shuffle = (Button) findViewById(R.id.button_shuffle);

        new AsyncComplex(Game6x5Activity.this).execute(URL_STRING);
    }

    //upon retrieving JSON object, extracts images from it
    //then assigns images to each button
    @Override
    public void onTaskComplete(String result){
        extractImageUrls(result);

        //display image on each button
        buttons = new ArrayList<>();
        initializeBoard(buttons);

        //shuffle cards when shuffle button is clicked
        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(ImageButton b : buttons){
                    Random random = new Random();
                    int idx = random.nextInt(buttons.size());
                    if(!matchedCards.contains(b) && !matchedCards.contains(buttons.get(idx))){
                        Card temp = hm.get(b);
                        hm.put(b, hm.get(buttons.get(idx)));
                        hm.put(buttons.get(idx), temp);
                    }
                }
            }
        });
    }

    //input: JSON String
    //output: void (extracts image src url from JSON object into arraylist)
    private void extractImageUrls(String result){
        try {
            JSONObject jsonObj = new JSONObject(result);
            JSONArray products = jsonObj.getJSONArray("products");
            for (int i = 0; i < NUM_IMGS; i++) {
                JSONObject p = products.getJSONObject(i);
                JSONObject image = p.getJSONObject("image");
                String image_url = image.getString("src");

                img_urls.add(image_url);
            }

            //bandaid solution for same imgs from different urls
            JSONObject p = products.getJSONObject(NUM_IMGS);
            JSONObject image = p.getJSONObject("image");
            String img_url = image.getString("src");

            img_urls.set(11, img_url);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //input: arraylist of buttons representing cards
    //output: void (set images, onClickListener logic to each button)
    private void initializeBoard(ArrayList<ImageButton> buttons){

        //assign images to buttons
        buttons.add((ImageButton)findViewById(R.id.imageButton1));
        buttons.add((ImageButton)findViewById(R.id.imageButton2));
        buttons.add((ImageButton)findViewById(R.id.imageButton3));
        buttons.add((ImageButton)findViewById(R.id.imageButton4));
        buttons.add((ImageButton)findViewById(R.id.imageButton5));
        buttons.add((ImageButton)findViewById(R.id.imageButton6));
        buttons.add((ImageButton)findViewById(R.id.imageButton7));
        buttons.add((ImageButton)findViewById(R.id.imageButton8));
        buttons.add((ImageButton)findViewById(R.id.imageButton9));
        buttons.add((ImageButton)findViewById(R.id.imageButton10));
        buttons.add((ImageButton)findViewById(R.id.imageButton11));
        buttons.add((ImageButton)findViewById(R.id.imageButton12));
        buttons.add((ImageButton)findViewById(R.id.imageButton13));
        buttons.add((ImageButton)findViewById(R.id.imageButton14));
        buttons.add((ImageButton)findViewById(R.id.imageButton15));
        buttons.add((ImageButton)findViewById(R.id.imageButton16));
        buttons.add((ImageButton)findViewById(R.id.imageButton17));
        buttons.add((ImageButton)findViewById(R.id.imageButton18));
        buttons.add((ImageButton)findViewById(R.id.imageButton19));
        buttons.add((ImageButton)findViewById(R.id.imageButton20));
        buttons.add((ImageButton)findViewById(R.id.imageButton21));
        buttons.add((ImageButton)findViewById(R.id.imageButton22));
        buttons.add((ImageButton)findViewById(R.id.imageButton23));
        buttons.add((ImageButton)findViewById(R.id.imageButton24));
        buttons.add((ImageButton)findViewById(R.id.imageButton25));
        buttons.add((ImageButton)findViewById(R.id.imageButton26));
        buttons.add((ImageButton)findViewById(R.id.imageButton27));
        buttons.add((ImageButton)findViewById(R.id.imageButton28));
        buttons.add((ImageButton)findViewById(R.id.imageButton29));
        buttons.add((ImageButton)findViewById(R.id.imageButton30));

        Collections.shuffle(buttons, new Random());

        //set onClickListener logic for the buttons
        hm = new HashMap<>();
        matchedCards = new HashSet<>();
        isBoardLocked = false;
        firstCard = null;

        //assign each img_id to two of the buttons
        //onClickListener: for every two cards flipped, check if they are a match
        for(int i = 0; i < buttons.size(); i++) {

            final int img_id = i % img_urls.size();
            final ImageButton card = buttons.get(i);
            hm.put(card, new Card(img_id, true));

            card.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    if(!canFlipCard(card))
                        return;

                    //update the scoreboard
                    flips.setText("Flips: " + Integer.toString(++flip_count));

                    //if current card is first of the two, keep record and flip it
                    if(firstCard == null){
                        firstCard = card;
                        Picasso.get().load(img_urls.get(hm.get(firstCard).img_id)).into(firstCard);
                        return;
                    }

                    //if current card is second of the two, check if it's a match
                    if(hm.get(firstCard).img_id == hm.get(card).img_id){
                        Picasso.get().load(img_urls.get(hm.get(card).img_id)).into(card);
                        processCardMatch(firstCard, card);
                    }
                    else{
                        processCardNoMatch(firstCard, card);
                    }
                }

                private boolean canFlipCard(ImageButton card){
                    return !isBoardLocked && (card != firstCard) && !matchedCards.contains(card);
                }

                private void processCardMatch(ImageButton card1, ImageButton card2){
                    matchedCards.add(card1);
                    matchedCards.add(card2);
                    card1.setEnabled(false);
                    card2.setEnabled(false);
                    matches.setText("Matches: " + matchedCards.size()/2);
                    firstCard = null;
                    if(matchedCards.size() == 2*NUM_IMGS){
                        builder.setMessage("Do you want to play again?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.setTitle("You Win!!!");
                        alert.show();
                    }
                }

                private void processCardNoMatch(ImageButton card1, ImageButton card2){
                    isBoardLocked = true;
                    firstCard = card1;
                    secondCard = card2;
                    Picasso.get().load(img_urls.get(hm.get(secondCard).img_id)).into(secondCard);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable (){
                        @Override
                        public void run(){
                            firstCard.setImageResource(0);
                            secondCard.setImageResource(0);
                            firstCard = null;
                            secondCard = null;
                            isBoardLocked = false;
                        }
                    }, 1000);
                }
            });
        }
    }
}
