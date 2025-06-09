import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { catchError, Observable, throwError } from "rxjs";
// import { API_URL_RL } from "../../constants/url";
 import { K8ExternalIp } from "../../constants/url";

@Injectable({
    providedIn: 'root'
})
export class RestaurantService {

     private apiUrl = K8ExternalIp+'/restaurant/fetchAllRestaurants';

    // private apiUrl = API_URL_RL+'/restaurant/fetchAllRestaurants';
    // un comment when running in local

    constructor(private http: HttpClient){

    }

    getAllRestaurants(): Observable<any> {

        return this.http.get<any>(`${this.apiUrl}`)
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