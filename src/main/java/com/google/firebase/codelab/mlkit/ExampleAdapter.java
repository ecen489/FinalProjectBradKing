package com.google.firebase.codelab.mlkit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>
    {
        private ArrayList<ExampleItem> mExampleList;
        public static class ExampleViewHolder extends RecyclerView.ViewHolder
            {
                public TextView mTextView1;
//                public GraphicOverlay.Graphic  mGraphicView1;
                public GraphicOverlay mG;
                public ExampleViewHolder(@NonNull View itemView)
                    {
                        super(itemView);
                        mTextView1= itemView.findViewById(R.id.text_viewR);
//                        mGraphicView1 = itemView.findViewById(R.id.graphicOverlayRecycler);
                        mG = itemView.findViewById(R.id.graphicOverlayRecycler);
                    }
            }


        public ExampleAdapter(ArrayList<ExampleItem> exampleList)
            {
                mExampleList = exampleList;
             }

        @NonNull
        @Override
        public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
            {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
                ExampleViewHolder evh = new ExampleViewHolder(v);
                return evh;
            }

        @Override
        public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position)
            {
                ExampleItem currentItem = mExampleList.get(position);
                holder.mTextView1.setText(currentItem.getmText1());
//                holder.mG.add(currentItem.getmGraphicOverlay());
            }

        @Override
        public int getItemCount()
            {
                return mExampleList.size();
            }


    }
