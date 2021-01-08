package com.example.superfarmerapk

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ColorSpace
import android.graphics.PixelFormat
import android.os.Build
import android.webkit.ConsoleMessage
import android.widget.Toast
import androidx.annotation.RequiresApi

object ImageManagement
{




    @RequiresApi(Build.VERSION_CODES.Q)
    fun ResizedImage(image: Bitmap):Bitmap
    {
        var newimage = Bitmap.createScaledBitmap(image,512/4,768/4, true)
        var h = newimage.height
        var w = newimage.width
          var newnewimage = Bitmap.createBitmap(image,1,1,4,9)
        return newimage



    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun ImageToCyan(image: Bitmap) :Bitmap {
        var h = image.height-1
        var w = image.width-1
        var newimage = Bitmap.createBitmap(image, 1, 1, w, h)
        for (i in 1..w)
        {
            for (j in 1..h)
            {
                var color: Color = image.getColor(i,j)
                if(color.red()>150 && color.green()>150 && color.blue()>150 )
                {
                 newimage.setPixel(i,j, Color.CYAN)
                    println("cyjanowy")
                }
            }
        }
        return newimage
    }




    fun CutToPieces(image:Bitmap, w:Int, h:Int)
    {
        //podzielenie obrazu na na kawalki, ktore pozniej zostaną jednym kolorem
        var pieces = mutableListOf<Bitmap>()
        for(i in 1..w step 16)

            for(j in 1..h step 16)
            {
                //newimage to malutki fragment, ktory zostanie zamieniony na jeden kolor
                var newimage = Bitmap.createBitmap(image,1*i,1*j,i+4,j+9)
                pieces.add(newimage)
                var newpixel = Bitmap.createBitmap(newimage,1,1,1,1)
            }

        //    for(i in 1..pieces.count())
        //    {
        //        var im = pieces[i
        //     }
    }


}