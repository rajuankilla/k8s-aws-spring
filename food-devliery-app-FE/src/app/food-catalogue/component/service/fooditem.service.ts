import { Injectable } from "@angular/core"; 
import { HttpClient } from "@angular/common/http";
import { catchError, Observable, throwError } from "rxjs";
import { API_URL_FC, API_URL_RL } from "../../../constants/url";

@Injectable({
    providedIn: 'root'
})
export class FoodItemService {

    private apiUrl = API_URL_FC+'/foodCatalogue/fetchRestaurantAndFoodItemsById/';

    constructor(private http: HttpClient){

    }

    getFoodItemsByRestaurant(id: number): Observable<any> {

        return this.http.get<any>(`${this.apiUrl+id}`)
        .pipe(
            catchError(this.handleError)
        );
    }
    
    private handleError(error: any)
    
    {
        console.log('An error occured: ',error);
        return throwError(error.message || error)
    }

}