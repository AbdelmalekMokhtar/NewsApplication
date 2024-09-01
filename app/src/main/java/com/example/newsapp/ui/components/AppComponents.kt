package com.example.newsapp.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.data.model.Article

@Composable
fun Loader(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp),
            color = Color(0xFF6650a4)
        )
    }
}


@Composable
fun NormalTextComponent(textValue: String){
    Text(
        text = textValue,
        modifier = Modifier
            .fillMaxWidth()
            //  .wrapContentHeight()
            .padding(8.dp),
        style = TextStyle(
            color = Color(0xFF6650a4),
            fontSize = 18.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Normal
        )
    )
}

@Composable
fun HeadingTextComponent(textValue: String){
    Text(
        text = textValue,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        style = TextStyle(
            color = Color(0xFFD0BCFF),
            fontFamily = FontFamily.Serif,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    )

}


@Composable
fun AuthorDetailsComponent(authorName: String?){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        authorName?.also {
            Text(text  = it,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Cursive,
                )
            )
        }

//        sourceName?.also {
//            Text(text = it)
//        }

    }
}

@Composable
fun OpenLinkButton(article: Article) {
    val context = LocalContext.current

    TextButton(
        modifier = Modifier.fillMaxWidth(),
                onClick = {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
        context.startActivity(intent)
    }) {
        Text("For more...",
            style = TextStyle(
                color = Color.Yellow,
                fontSize = 18.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal
            )
        )
    }
}


@Composable
fun NewsRowComponent(page: Int, article: Article){

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            ,
    ){
        Spacer(modifier = Modifier.height(30.dp))

        NormalTextComponent(textValue ="$page")

        AsyncImage(
            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth()
                .clickable { },
            model =article.urlToImage,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            placeholder = painterResource(id = R.drawable.imageph),
            error = painterResource(id = R.drawable.imageph)
        )

        NormalTextComponent(textValue = ("At: " + article.publishedAt) )

        Spacer(modifier = Modifier.height(10.dp))

        HeadingTextComponent(textValue = article.title?:"")

        Spacer(modifier = Modifier.height(20.dp))

        NormalTextComponent(textValue = article.content?:"")

        Spacer(modifier = Modifier.height(20.dp))

        AuthorDetailsComponent(authorName = article.author)

        OpenLinkButton(article)

    }
}

