import { TestBed } from '@angular/core/testing';

import { FoodCatalogService } from './food-catalog-service';

describe('FoodCatalogueService', () => {
  let service: FoodCatalogService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FoodCatalogService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
