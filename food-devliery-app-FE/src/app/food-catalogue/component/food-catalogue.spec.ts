import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodCatalogue } from './food-catalogue';

describe('FoodCatalogue', () => {
  let component: FoodCatalogue;
  let fixture: ComponentFixture<FoodCatalogue>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FoodCatalogue]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FoodCatalogue);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
