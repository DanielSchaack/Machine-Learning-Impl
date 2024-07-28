# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.0.9] 2024-07-27
### ADDED
### CHANGED
- SingleModelPipeline (should) properly calculates the gradient of the batch
    - Moved Optimizer back into ModelSettigns
- Renamed some of the derivative functions
- Logging messages now include method name and line number (also fixed PARSER_ERROR by renaming log to logger?)
### TODO
- EvaluationFunction
    - Implementation for BinaryClasification
    - Use inside SingleModelPipeline
- Unit tests
- Documentation
- Gaussian Distribution function
    - Logic to draw values from the gaussian distribution
- Perceptron example for XOR, see PerceptronXorModel.java
- Xavier-Initialisation
- He-Initialisation
- Refactor Model, ModelSettings?

## [0.0.8] 2024-07-27
### ADDED
- Optimiser interfaces
- Stochastic Gradient Descent as an optimizer
- ZeroWeightInitialisation as an initialiser
- LossFunction expended by functions
- BCE as a loss
- DataLoaderInterface expended by functions
- Simple Matrix/array functions
- DefaultDataLoader as an implementation
- SingleModelPipeline as the foundation of training a model
    - Perceptron Implementation
### CHANGED
- Default for flushing the logs set to false
- Changed individual predicts from Model interfaces into a generic in the parent interface
- Functions for ModelSettings moved to SingleModelPipeline
### REMOVED
- BaseModel 
- Classifier-, Regressor, Clusterer-Interface
- LoggingUtils
### TODO
- EvaluationFunction
    - Implementation for BinaryClasification
- Unit tests
- Gaussian Distribution function
- Perceptron example for XOR, see PerceptronXorModel.java

## [0.0.7] 2024-07-22
### CHANGED
- Folder structure into subject/implementation and subject/interfaces

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