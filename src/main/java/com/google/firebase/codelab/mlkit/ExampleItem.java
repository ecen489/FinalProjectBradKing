package com.google.firebase.codelab.mlkit;

public class ExampleItem
    {
        private String mText1;
        private GraphicOverlay mGraphicOverlay;
//        private GraphicOverlay.Graphic mGraphicOverlay;
        public ExampleItem(){}
        public ExampleItem(String myText1)
            {
                mText1 =myText1;
//                mGraphicOverlay = mGraph;
            }

        public String getmText1()
            {
                return mText1;
            }

        public GraphicOverlay getmGraphicOverlay()
            {
                return mGraphicOverlay;
            }
    }
