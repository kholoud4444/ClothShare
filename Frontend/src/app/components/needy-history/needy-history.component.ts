import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {Needy} from '../model/needy';
import {NeedyService} from '../services/needy.service';
import { provideHttpClient  } from '@angular/common/http';
import {AsyncPipe, NgForOf, NgIf} from '@angular/common';
import {map, Observable} from 'rxjs';
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
export class NeedyHistoryComponent  {
  needies! : Observable<Array<Needy>>;

  errorMessage!: string;
  constructor(private needyService : NeedyService, private fb : FormBuilder, private router : Router) { }

  handleDeleteNeedy(n: Needy) {
    let conf = confirm("Are you sure?");
    if(!conf) return;
    this.needyService.deleteNeedyById(n.id).subscribe({
      next : (resp) => {
        this.needies=this.needies.pipe(
          map(data=>{
            let index=data.indexOf(n);
            data.slice(index,1)
            return data;
          })
        );
      },
      error : err => {
        console.log(err);
      }
    })
  }


}
