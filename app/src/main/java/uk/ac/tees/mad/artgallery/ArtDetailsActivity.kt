package uk.ac.tees.mad.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import uk.ac.tees.mad.artgallery.ui.ArtDetailsScreen
import uk.ac.tees.mad.artgallery.ui.homeScreen.model.Record
import uk.ac.tees.mad.artgallery.ui.theme.ArtGalleryTheme

class ArtDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val artDetails = intent.getSerializableExtra("art_detail") as Record

        setContent {
            ArtGalleryTheme {
                ArtDetailsScreen(artDetails)
            }
        }
    }
}