import { Component, OnInit } from '@angular/core';
import { FormBuilder } from "@angular/forms";
import { Router } from "@angular/router";
import { Needy } from '../model/needy';
import { NeedyService } from '../../services/needy.service';
import { AsyncPipe, NgForOf, NgIf } from '@angular/common';
import { map, Observable } from 'rxjs';

@Component({
  selector: 'app-needy-history',
  templateUrl: './needy-history.component.html',
  standalone: true,
  styleUrls: ['./needy-history.component.scss'],
  imports: [
    NgForOf,
    AsyncPipe,
    NgIf
  ]
})
export class NeedyHistoryComponent implements OnInit {
  needies!: Observable<Array<Needy>>;
  errorMessage!: string;

  constructor(
    private needyService: NeedyService,
    private fb: FormBuilder,
    private router: Router
  ) { }

  ngOnInit() {
    this.loadNeedies();
  }

  loadNeedies() {
    this.needies = this.needyService.getAllNeedy();

  }

  handleDeleteRequest(n: Needy) {
    let conf = confirm("Are you sure?");
    if (!conf) return;
    this.needyService.deleteRequestById(n.requestId).subscribe({
      next: () => {
        this.loadNeedies(); // Reload needies after deletion
      },
      error: err => {
        console.log(err);
      }
    });
  }
}
