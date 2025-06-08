import { Injectable } from "@angular/core";
import { API_URL_Order, API_URL_RL } from "../../constants/url";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { catchError, Observable, throwError } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class OrderService {

    private apiUrl = API_URL_Order+'/order/saveOrder';
    constructor(private http: HttpClient){  }

    httpOptions={
        headers: new HttpHeaders({
            'Content-Type': 'text/plain',
            'Access-Control-Allow-Origin': 'http://localhost:4200' 
        })
    };

    saveOrder(data: any):Observable<any>{
        return this.http.post<any>(this.apiUrl, data);
    }

     

}