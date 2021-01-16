package com.example.superfarmerapk

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import org.opencv.android.OpenCVLoader
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Scalar
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import java.security.AccessController.getContext


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
        var h = image.height-2
        var w = image.width-2
        var newimage = Bitmap.createBitmap(image, 0, 0, w+1, h+1)
        var found= false
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
            }
        }

        for(x in 1..w)
            for(y in 1..h)
            {
                var p = image.getPixel(x,y)
                var pp = image.getPixel(x-1,y-1)
                var redValue = Color.red(p);
                var blueValue = Color.blue(p);
                var greenValue = Color.green(p);
                var redValue2 = Color.red(pp);
                var blueValue2 = Color.blue(pp);
                var greenValue2 = Color.green(pp);
                //jesli nie zostal znaleziony pierwszy cyanowy pixel
                if(!found)
                {
                    //jesli pixel jest cyanowy, a jeden wczesniej nie jest (x-1,y-1)
                if(greenValue>150 && blueValue>150
                    && !(greenValue2>150 && blueValue2>150))
                    {
                        println("------------------tuuuu jestem!!!----------------------")
                        newimage.setPixel(x,x, Color.rgb(255,40,40))
                        newimage.setPixel(x+1,x, Color.rgb(255,40,40))
                        newimage.setPixel(x,x+1, Color.rgb(255,40,40))
                        newimage.setPixel(x+1,x+1, Color.rgb(255,40,40))
                        found=true
                    }
                }
                //jesli jest znaleziony pierwszy cyanowy pixel
          /*      if(found)
                {
                    var ppp = image.getPixel(x+1,y+1)
                    var redValue3 = Color.red(ppp);
                    var blueValue3 = Color.blue(ppp);
                    var greenValue3 = Color.green(ppp);
                    if(redValue>150 && greenValue>150 && blueValue>150
                        && !(redValue3>150 && greenValue3>150 && blueValue3>150)) {
                        newimage.setPixel(x, x, Color.rgb(255, 40, 40))
                    }
                }*/
            }

        return newimage
    }


    //znajduje kostke typu "od pierwszego cyan do ostatniego cyan"


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


    fun ReadImageOpenCV()
    {

        val srcpath =
            "D:\\STUDIA\\0.INF\\I5. IO\\Nowy folder\\SuperFarmer\\zdjecia\\"
        val tempath =
            "D:\\STUDIA\\0.INF\\I5. IO\\Nowy folder\\SuperFarmer\\templatki\\"

        var source = Imgcodecs.imread(srcpath + "zdjecie.jpg")
        var template = Imgcodecs.imread(tempath + "krolik.jpg")

        val outputImage = Mat()
        val machMethod = Imgproc.TM_CCOEFF
        //Template matching method
        //Template matching method
        Imgproc.matchTemplate(source, template, outputImage, machMethod)


        val mmr = Core.minMaxLoc(outputImage)
        val matchLoc: Point = mmr.maxLoc
        //Draw rectangle on result image
        //Draw rectangle on result image
        Imgproc.rectangle(
            source, matchLoc, Point(
                matchLoc.x + template.cols(),
                matchLoc.y + template.rows()
            ), Scalar(255.0, 255.0, 255.0)
        )

        Imgcodecs.imwrite(srcpath.toString() + "sonuc.jpg", source)
        println("Complated.")
    }

}