#version 440 core

layout(location=0) in vec4 position;
layout(location=1) in vec4 color;

out PIPELINE {
	vec4 color;
} pipeline;

void main(void)
{
    gl_Position = position;
    pipeline.color = color;
}



