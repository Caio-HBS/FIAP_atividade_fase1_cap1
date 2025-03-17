package br.com.fiap.carrerup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.carrerup.ui.theme.DeepBlue

@Composable
fun LazyStaggeredGridItem(
    colorList: List<Color>,
    heightsList: List<Dp>,
    info: Map<String, String>
) {

    val infoList = info.toList()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .padding(10.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(heightsList.size) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(heightsList[index])
                    .clip(RoundedCornerShape(10.dp))
                    .background(colorList[index])
                    .clickable {},
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                ) {
                    Text(
                        text = infoList[index].first,
                        fontSize = 25.sp,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Black,
                        color = DeepBlue
                    )
                    Text(
                        text = if (infoList[index].second != "") infoList[index].second else "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean sed augue eu risus pulvinar lobortis sed at justo. Proin imperdiet.",
                        color = DeepBlue
                    )
                }
            }
        }
    }

}