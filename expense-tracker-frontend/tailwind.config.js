/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        my_light_green:"#47CF73",
        my_dark_green:"#248C46",
        my_bg:"#121315",
        my_bg_white:"#ffffff",  
        my_bg_silver:"#cccccc ",
        my_box:"#252830",
        my_font1:"#ffffff",
        my_font2:"#252830"
      }
    },
  },
  plugins: [],
}