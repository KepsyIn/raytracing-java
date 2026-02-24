# Raytracing Java

A 3D raytracing engine in Java that renders photorealistic scenes with ray casting, realistic lighting, reflections, and anti-aliasing.

## Overview

This project implements ray tracing algorithms to compute light paths through 3D scenes. It features geometric primitives (spheres and planes), dynamic light sources, reflective surfaces, and anti-aliasing for high-quality rendering.

## Project Structure

```
raytracing-java/
├── model/           # Geometric primitives (Sphere, Plan)
├── raytracing/      # Core ray tracing engine (Scene, LightSource)
├── render/          # Image export (RenderTga)
├── demo/            # Scene creation and management
├── utils/           # Vector mathematics (Vec3)
└── main.java        # Application entry point
```

All classes include comprehensive Javadoc documentation for implementation details.

## Features

- **Ray-Object Intersection** - Accurate sphere and plane collision detection
- **Phong Illumination** - Direct and specular lighting from multiple light sources
- **Surface Reflection** - Configurable reflection coefficients and recursive ray bouncing
- **Anti-aliasing** - Multi-sample supersampling for smooth output
- **TGA Export** - Renders to TGA image format (800x600 by default)

## Compile 

Use the makefile to compile the project or build it manually with:

```bash
javac -d bin main.java
```

## Usage

Run with a scene number (1-5):

```bash
java -cp bin main <scene_number>
```

Get help:
```bash
java -cp bin main -h
```

## Output

The program returns a byte buffer containing the rendered image data. This buffer can be rendered/saved in various image formats, such as TGA files. By default, the program saves the output as `raytracing_scene.tga` (800x600 pixels with RGB color depth). 