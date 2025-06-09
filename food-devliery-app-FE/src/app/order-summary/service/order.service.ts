import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { catchError, Observable, throwError } from "rxjs";
// import { API_URL_Order, API_URL_RL } from "../../constants/url";
 import { K8ExternalIp } from "../../constants/url";

@Injectable({
    providedIn: 'root'
})
export class OrderService {

    // private apiUrl = API_URL_Order+'/order/saveOrder';
    // un comment when running in local
    
     private apiUrl = K8ExternalIp+'/order/saveOrder';

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