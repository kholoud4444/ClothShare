import { Component, OnInit } from '@angular/core';
import { NgForOf, NgStyle } from '@angular/common';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [NgStyle, NgForOf],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  images = [
    { src: 'assets/images/sbk3.jpg', alt: 'First slide' ,label: 'كساء الخير', description:'رسالة إنسانية تربط القلوب وتجسد معاني التراحم والتعاضد'},
    { src: 'assets/images/sbk4.jpg', alt: 'Second slide' ,label: 'كساء الخير' , description:'رسالة إنسانية تربط القلوب وتجسد معاني التراحم والتعاضد'},
    { src: 'assets/images/sbk5.jpg', alt: 'Third slide' ,label: 'كساء الخير' , description:'رسالة إنسانية تربط القلوب وتجسد معاني التراحم والتعاضد'},
    // { src: 'assets/images/sbk6.jpg', alt: 'Third slide' ,label: 'كساء الخير' , description:'رسالة إنسانية تربط القلوب وتجسد معاني التراحم والتعاضد'}

  ];


  currentSlide = 0; // Start with the first slide
  isTransitioning = false; // To prevent multiple clicks during transition

  ngOnInit(): void {
    this.startSlideRotation();
  }

  startSlideRotation(): void {
    setInterval(() => {
      if (!this.isTransitioning) {
        this.nextSlide();
      }
    }, 4000); // Automatically rotate every 4 seconds
  }

  nextSlide(): void {
    if (this.isTransitioning) return;
    this.isTransitioning = true;

    this.currentSlide = (this.currentSlide + 1) % this.images.length;

    // Reset transition flag after the animation duration
    setTimeout(() => {
      this.isTransitioning = false;
    }, 600); // Match this duration with the CSS transition time
  }

  prevSlide(): void {
    if (this.isTransitioning) return;
    this.isTransitioning = true;

    this.currentSlide = (this.currentSlide - 1 + this.images.length) % this.images.length;

    // Reset transition flag after the animation duration
    setTimeout(() => {
      this.isTransitioning = false;
    }, 600); // Match this duration with the CSS transition time
  }

  goToSlide(index: number): void {
    if (this.isTransitioning) return;
    this.isTransitioning = true;

    this.currentSlide = index;

    // Reset transition flag after the animation duration
    setTimeout(() => {
      this.isTransitioning = false;
    }, 200); // Match this duration with the CSS transition time
  }
}
