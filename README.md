# Mobile-Development

# MyPersonalColor Application

## Overview
MyPersonalColor is a comprehensive mobile application designed to assist users in discovering their personal color palette, dress recommendations, makeup suggestions, and more, tailored to their unique skin tone. The application utilizes advanced image processing and machine learning algorithms to analyze users' facial skin tone, providing personalized insights and style recommendations.

[Demo App Video](https://drive.google.com/file/d/1NhPWN-GuwRsbOH-nsVPTil47938_W-VU/view?usp=sharing)

## Features

### 1. Skin Tone Analysis
- **Automatic Skin Tone Detection**: Users can capture a selfie using the in-app camera functionality. The application then automatically analyzes the skin tone by sending the image to a server, where advanced algorithms determine the user's skin tone category.
- **Personalized Color Palette Recommendations**: Based on the skin tone analysis, the app provides a customized color palette suitable for the user. These recommendations include a variety of colors that complement the user's skin tone, enhancing their personal style.

### 2. Dress Recommendations
- **Tailored Fashion Advice**: Post skin tone analysis, users gain access to dress recommendations. This feature suggests clothing items that match the user's personal color palette, offering ideas for outfits that will look great on them.
- **Integration with External Fashion Databases**: The app fetches dress suggestions from an external API, ensuring up-to-date and trendy fashion recommendations.

### 3. Makeup Recommendations
- **Custom Makeup Suggestions**: Users receive makeup product recommendations that align with their skin tone category. This feature simplifies the process of choosing the right makeup, ensuring products complement the user's natural complexion.

### 4. Color Classification and Palettes
- **Detailed Color Classifications**: The app provides detailed classifications of colors based on the user's skin tone category, offering a broader understanding of colors that suit them best.
- **Visual Color Palette Display**: Each color classification is visually represented in the app, making it easy for users to understand and apply the color suggestions in their daily wardrobe and makeup choices.

## Technical Implementation

- **Firebase Authentication and Firestore Database**: The app uses Firebase for user authentication and data storage. Users' skin tone categories and other personalized data are securely stored in Firestore.
- **Camera and Image Processing**: Integrated camera functionality allows users to take selfies directly in the app. The images are processed to analyze skin tones accurately.
- **External API Integration**: The application connects with external APIs for real-time fashion and makeup recommendations, ensuring that the suggestions are current and trendy.
- **Responsive and User-Friendly Interface**: Designed with a focus on user experience, the app features a clean and intuitive interface, making navigation and usage effortless for users of all ages.

## Future Enhancements

- **Expanded Fashion and Makeup Databases**: Future updates will include a broader range of fashion and makeup products, offering more diverse recommendations.
- **AI-Enhanced Style Assistant**: Plans to implement an AI-driven style assistant that can provide real-time advice and tips based on user preferences and trending styles.
- **Community Features**: Introducing social features where users can share their looks, rate recommendations, and interact with a community of like-minded fashion enthusiasts.

## Installation and Setup

1. Clone the repository from GitHub.
2. Open the project in Android Studio.
3. Configure Firebase and other necessary APIs.
4. Build and run the application on an emulator or physical device.
---

MyPersonalColor aims to revolutionize the way individuals approach personal style, making fashion and beauty choices more personalized and confident. The fusion of technology and style opens up a world of possibilities for users to explore and embrace their unique beauty.
