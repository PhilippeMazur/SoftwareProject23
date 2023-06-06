import { TestBed } from '@angular/core/testing';

import { AuthorizationInterceptorInterceptor } from './authorization-interceptor.interceptor';

describe('AuthorizationInterceptorInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      AuthorizationInterceptorInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: AuthorizationInterceptorInterceptor = TestBed.inject(AuthorizationInterceptorInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
