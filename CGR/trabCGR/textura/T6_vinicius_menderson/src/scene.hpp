#ifndef __RT_SCENE__
#define __RT_SCENE__

#include "objects.hpp"

void InitDefaultScene()
{
	Planes.push_back({{{ 1, 0, 0}}, -30, {{{1.00, 0.25, 0.25}}, 0.20, 0.11, false, 1}});
	Planes.push_back({{{ 0,-1, 0}}, -30, {{{1.00, 1.00, 1.00}}, 0.20, 0.11, false, 1}});
	Planes.push_back({{{-1, 0, 0}}, -30, {{{0.25, 1.00, 0.25}}, 0.20, 0.11, false, 1}});
	Planes.push_back({{{ 0, 1, 0}}, -30, {{{0.06, 0.06, 0.05}}, 0.40, 0.05, false, 1}});
	Planes.push_back({{{ 0, 0, 1}}, -30, {{{0.25, 0.25, 1.00}}, 0.20, 0.11, false, 1}});
	Planes.push_back({{{ 0, 0,-1}}, -90, {{{1.00, 0.25, 1.00}}, 0.20, 0.11, false, 1}});

	// Spheres.push_back({{{  0.0,  0.0,  0.0}},  7.0, {{{1,1,1}}, 0.01, 0.94, false, 1}});
	// Spheres.push_back({{{ 19.4,-19.4,  0.0}},  2.1, {{{1,1,1}}, 0.01, 0.94, false, 1}});
	// Spheres.push_back({{{ 13.1,  5.1,  0.0}},  1.1, {{{1,1,1}}, 0.01, 0.94, false, 1}});
	// Spheres.push_back({{{ -5.1,-13.1,  0.0}},  1.1, {{{1,1,1}}, 0.01, 0.94, false, 1}});
	// Spheres.push_back({{{-18.5, 19.0, 15.0}}, 11.0, {{{1,1,1}}, 0.01, 0.94, false, 1}});
	// Spheres.push_back({{{ 15.0,-30.0, 30.0}},  6.0, {{{1,1,1}}, 0.01, 0.94, false, 1}});
	// Spheres.push_back({{{ 30.0, 15.0,-30.0}},  6.0, {{{1,1,1}}, 0.01, 0.94, false, 1}});
	// Spheres.push_back({{{ 10.0, 23.0, 40.0}}, 10.0, {{{1,1,1}}, 0.02, 0.94, false, 1}});
	// Spheres.push_back({{{ 10.0,  8.0,-18.0}},  7.0, {{{1,1,1}}, 0.30, 0.75, false, 1}});
	// Spheres.push_back({{{  8.0, 17.0,-10.0}},  8.0, {{{1,1,1}}, 0.00, 0.85, true,1.5}});
	
	Lights.push_back({{{-28,-14,  3}}, {{ .40, .51, .90}}});
	Lights.push_back({{{-29,-29,-29}}, {{ .60, .60, .60}}});
	Lights.push_back({{{ 14, 29,-14}}, {{ .80, .80, .80}}});
	Lights.push_back({{{ 29, 29, 29}}, {{1.00,1.00,1.00}}});
	Lights.push_back({{{ 28,  0, 29}}, {{ .50, .60, .20}}});
	Lights.push_back({{{  0,-29,  0}}, {{ .95, .95, .95}}});
}

#endif
