import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RestaurantListingRoutingModule } from './restaurant-listing-routing-module';
import { RestaurantListing } from './component/restaurant-listing';


@NgModule({
  declarations: [
    RestaurantListing
  ],
  imports: [
    CommonModule,
    RestaurantListingRoutingModule
  ] ,
  exports: [
    RestaurantListing
  ]
})
export class RestaurantListingModule { }
