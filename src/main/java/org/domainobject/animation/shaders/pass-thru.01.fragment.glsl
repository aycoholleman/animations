#version 450 core
 
in PIPELINE {
	vec4 color;
} pipeline;

out vec4 color;
 
void main(void)
{
    color = pipeline.color;
}
