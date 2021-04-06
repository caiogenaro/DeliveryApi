import { TestBed } from '@angular/core/testing';

import { RestauranteFormService } from './restaurante-form.service';

describe('RestauranteFormService', () => {
  let service: RestauranteFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestauranteFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
