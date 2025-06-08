import { ChangeDetectorRef, Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderDTO } from '../modals/OrderDTO';
import { OrderService } from '../service/order.service';

@Component({
  selector: 'app-order-summary',
  standalone: false,
  templateUrl: './order-summary.html',
  styleUrl: './order-summary.css'
})
export class OrderSummary {

  orderSummary?: OrderDTO;
  obj: any;
  total?: any;
  showDialog?: boolean=false;


  constructor(private route: ActivatedRoute, private orderService: OrderService, private router: Router, private cdr: ChangeDetectorRef){}

  ngOnInit(){
  const data = this.route.snapshot.queryParams['data'];

    this.obj=JSON.parse(data);
    this.obj.userId=1;
    this.orderSummary=this.obj;

    this.total = this.orderSummary.foodItemsList.reduce((accumulator,currentValue) => {
      return accumulator + (currentValue.quantity * currentValue.price);
    },0);

  }
 
  saveOrder(){

    setTimeout(() => {
      this.orderService.saveOrder(this.orderSummary).
      subscribe(
        () => {
          this.showDialog=true;
          this.cdr.detectChanges();
        },
        error => {
          console.error("Failed to save data : ", error)
        }
      )
    })
  }

  closeDialog(){
    this.showDialog = false;
    this.router.navigate(['/']);
  }

}
