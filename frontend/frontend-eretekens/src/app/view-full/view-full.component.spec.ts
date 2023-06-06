import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFullComponent } from './view-full.component';

describe('ViewFullComponent', () => {
  let component: ViewFullComponent;
  let fixture: ComponentFixture<ViewFullComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewFullComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewFullComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
