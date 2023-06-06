import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryDeniedApplicationsComponent } from './history-denied-applications.component';

describe('HistoryDeniedApplicationsComponent', () => {
  let component: HistoryDeniedApplicationsComponent;
  let fixture: ComponentFixture<HistoryDeniedApplicationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryDeniedApplicationsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistoryDeniedApplicationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
