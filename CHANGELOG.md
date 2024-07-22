# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.0.6] 2024-07-22
### CHANGED
- Refactored data representation 
    - Allows for dependency injection
    - Implemented defaults for each interface
### FIXED
- logging

## [0.0.5] 2024-07-22
### CHANGED
- Refactored Logging to be configurable through logback (No longer reinventing the wheel)

## [0.0.4] 2024-07-20
### ADDED
- Model Interfaces and example implementation with a Perceptron
    - abstract BaseModel with:
        - ModelInterface
            - Classifier
            - Clusterer
            - Regressor
        - ModelSettings
            - PerceptronModelSettings
        
### CHANGED
- Refactored Logging
    - Now injectable into Models
    - Default stays as previous
- Renamed packages

## [0.0.3] 2024-07-20
### ADDED
- Basic buffered Logger setup
### CHANGED
- packages of varias classes

## [0.0.2] 2024-07-20
### ADDED
- Basic DataSet-setup
- Properties loader
    - Yaml-Parser using Snakeyaml
    - static ConfigReader

### REMOVED
- Lombok-dependency
    - manual Getters and toString implementations
- Spring-dependencies

## [0.0.1] 2024-07-20

### ADDED
- Basic draft of binary classification evaluation
    - Settings for parallel processing
    - Confusion matrix

### Changed
- Application-class is now a CommandLineRunner with sample code 

## [Unreleased]

### ADDED
- Basic setup using abstract classes and interfaces