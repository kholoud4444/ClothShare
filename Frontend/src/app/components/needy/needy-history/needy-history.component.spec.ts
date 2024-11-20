import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NeedyHistoryComponent } from './needy-history.component';

describe('NeedyHistoryComponent', () => {
  let component: NeedyHistoryComponent;
  let fixture: ComponentFixture<NeedyHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NeedyHistoryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NeedyHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
