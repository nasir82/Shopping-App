package com.pks.shoppingapp.core.presentation.components

//
//@Composable
//fun CartCard(modifier: Modifier = Modifier.fillMaxWidth(),cartItem:CartModel) {
//  Box(modifier = modifier
//      .padding(bottom = 10.dp)
//      .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp))
//      .height(125.dp))  {
//
//
//      Column(modifier = Modifier.fillMaxWidth()) {
//
//          Row(modifier = Modifier.fillMaxSize()) {
//              AsyncImage(
//                  model = ImageRequest.Builder(LocalContext.current)
//                      .data(cartItem.product.thumbnail)
//                      .crossfade(true) // Enables crossfade animation
//                      .build(),
//                  contentDescription = "",
//                  modifier = Modifier
//                      .width(75.dp)
//                      .clip(shape = RoundedCornerShape(10.dp)),
//                  contentScale = ContentScale.FillHeight
//              )
//              Spacer(modifier = Modifier.width(5.dp))
//              Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
//                  Column(
//                      modifier = Modifier.fillMaxSize(),
//                      verticalArrangement = Arrangement.Center
//                  ) {
//                      Text(
//                          text =cartItem. product.title,
//                          maxLines = 2,
//                          lineHeight = TextUnit(value = 14f, type = TextUnitType.Sp),
//                          overflow = TextOverflow.Ellipsis,
//                          fontSize = 12.sp
//                      )
//                      Text(text = cartItem.product.brand.name)
//                      Text(text = "XL")
//                      Text(text = "Blue")
//
//                  }
//              }
//
//              Row(modifier = Modifier.width(50.dp), horizontalArrangement = Arrangement.End) {
//                  Text(text =cartItem.product.salePrice.toString(), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onBackground)
//              }
//              Spacer(modifier = Modifier.width(5.dp))
//
//              Row(modifier = Modifier.width(30.dp), horizontalArrangement = Arrangement.Center) {
//                  Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally) {
//                      Text(text = "1")
//                      Column {
//                          IconButton(onClick = {
//                              if(cartItem.quantity<3){
//                                  //increase and set
//                              }else{
//                                  // say that max limit is 3
//                              }
//                          }) {
//                              Icon(
//                                  imageVector = Icons.Outlined.AddCircle,
//                                  contentDescription = "",
//                                  modifier = Modifier.size(15.dp),
//                                  tint = MaterialTheme.colorScheme.onBackground
//                              )
//                          }
//                          Spacer(modifier = Modifier.height(8.dp))
//                          IconButton(onClick = {
//                              if(cartItem.quantity==1){
//                                  //remove the cartItem
//                              }else{
//                                  //decrease
//                              }
//                          }) {
//                              Icon(imageVector = Icons.Outlined.RemoveCircleOutline, contentDescription = "", modifier = Modifier.size(15.dp))
//                          }
//
//                      }
//                  }
//              }
//              Spacer(modifier = Modifier.width(5.dp))
//              Row(modifier = Modifier.width(70.dp), horizontalArrangement = Arrangement.End) {
//                  Text(text = cartItem.product.salePrice.toString(), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onBackground)
//              }
//          }
//      }
//  }
//}