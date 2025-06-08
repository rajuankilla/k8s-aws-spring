import { ChangeDetectorRef, Component, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { Restaurant } from '../../shared/modals/Restaurant';
import { RestaurantService } from '../services/restaurant.service';

@Component({
  selector: 'app-restaurant-listing',
  standalone: false,
  templateUrl: './restaurant-listing.html',
  styleUrls: ['./restaurant-listing.css'],
  encapsulation: ViewEncapsulation.None
})
export class RestaurantListing {

  public restaurantList: Restaurant[];

  ngOnInit() {
    this.getAllRestaurants();
  }

  constructor(private router: Router, private restaurantService: RestaurantService, private cdr: ChangeDetectorRef) { }

  getAllRestaurants() {
    setTimeout(() => {
    this.restaurantService.getAllRestaurants().subscribe(
      data => {
        console.log("result : "+data);
        this.restaurantList = data;
        this.cdr.detectChanges();
      }
    );
     });
  }
  getRandomNumber(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }


  getRandomImage(): string {
    const imageCount = 8; // Adjust this number based on the number of images in your asset folder
    const randomIndex = this.getRandomNumber(1, imageCount);
    return `${randomIndex}.jpg`; // Replace with your image filename pattern
  }

  onButtonClick(id: number) {
    this.router.navigate(['/food-catalogue', id]);
  }


}
