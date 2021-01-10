package com.example.superfarmerapk

import android.R.attr.angle
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Matrix
import android.os.Build
import androidx.annotation.RequiresApi


object ImageManagement
{

 //   fun ResizedImage(image: Bitmap):Bitmap
  //  {
 //       var newimage = Bitmap.createScaledBitmap(image,512/4,768/4, true)
  //      var h = newimage.height
  //      var w = newimage.width
 //         var newnewimage = Bitmap.createBitmap(image,1,1,4,9)
//        return newimage
//    }

    //funkcja, ktora pozbywa się koloru bialego i pozwala pozniej wyznaczyc "kostki"
    //kolejnych zwierzat (bialy -> cyan)
    @RequiresApi(Build.VERSION_CODES.Q)
    fun ImageToCyan(image: Bitmap) :Bitmap {
        var h = image.height-1
        var w = image.width-1
        var newimage = Bitmap.createBitmap(image, 0, 0, w+1, h+1)
        for (x in 0..w)
        {
            for (y in 0..h)
            {

                var p = image.getPixel(x,y)
                var redValue = Color.red(p);
                var blueValue = Color.blue(p);
                var greenValue = Color.green(p);
                if(redValue>150 && greenValue>150 && blueValue>150)
                {
                    newimage.setPixel(x,y, Color.rgb(0,255,255))
                }
                //wyciecie zielonego, bo psy
            //    if(greenValue<140 && redValue<30 && blueValue<30)
           //         newimage.setPixel(x,y, Color.rgb(0,255,255))
            }
        }
        return newimage
    }


    //znajduje kostke typu "od pierwszego cyan do ostatniego cyan"
    @RequiresApi(Build.VERSION_CODES.Q)
    fun FindOneSector(image: Bitmap) :Bitmap {
        var newimage = Bitmap.createBitmap(image,0,0,image.width,image.height)
        var h = newimage.height-1
        var w = newimage.width-1
        var counterx = 0 //dlugosc w x
        var countery = 0 //dlugosc w y
        var startx = 0 // poczatek kostki w x
        var starty = 0 //poczatek kostki w y
        var endx = w //koniec w x
        var endy = h //koniec w y

        var found = false
        for (x in w..0)
      //      for (y in 0..h)
             {
                if (!found) {
                    var p = newimage.getPixel(x,x)
                    var redValue = Color.red(p);
                    var blueValue = Color.blue(p);
                    var greenValue = Color.green(p);
                    newimage.setPixel(x,x, Color.rgb(255, 30, 30))
                    //jesli pixel jest chociaz troche w strone cyanowego
                    if (greenValue > 240 && blueValue > 240) {
                        if ( x == h || x== w) break
                        startx = x
                        starty = x
                        newimage.setPixel(startx, starty, Color.rgb(255, 30, 30))
                        found=true
                    }
                }
            }
        /*
        for(x in 0..w) {
            if(x==w) break
            println(x.toString()+"-----"+w.toString())
            newimage.setPixel(x, starty, Color.rgb(255, 0, 0))
        }
        */
     //   counterx = 5*countery /4
     //   endx = startx+counterx
     //   endy = starty+countery
        // newimage.setPixel(startx-1,starty,Color.rgb(255,0,0) )
        return newimage
    }

//    fun Rotate(btm:Bitmap):Bitmap { //       val matrix = Matrix()
//        matrix.postRotate(90F)
//        return Bitmap.createBitmap(btm, 0, 0, btm.height, btm.width, matrix, false)
//    }



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