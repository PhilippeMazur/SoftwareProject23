import { TestBed } from '@angular/core/testing';

import { ABBService } from './abb.service';

describe('ABBService', () => {
  let service: ABBService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ABBService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
