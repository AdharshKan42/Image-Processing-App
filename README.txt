COMPLETED PARTS OF THE PROGRAM:
all of them, including:
-loading an image (from jpg, png, gif, ppm, or bmp)

-flipping an image (horizontally and vertically)

-greyscaling an image (using red, green, blue, value, intensity, or luma component)

-brightening/darkening an image

-saving an image (to jpg, png, gif, ppm, or bmp)

-transforming an image (greyscale or sepia)

-filtering an image (blurring or sharpening)

-downscaling an image

-GUI/histogram

DESIGN CHANGES:
-added GUIController, GUIModel, GUIView, GUITest, LouGram to implement
 GUI/histogram functionality

-added Downscale command/function objects and Model-level support for extra-credit purposes

-changed Pixel to keep track of alpha component 
(need to preserve it for 32-bit images, might be needed in the future)

-moved actual loading/saving functionality out of model and into controller
(we got points off for having it in model on A4)

-changed Pixel to have a 2D array of size [width][height] rather than [height][width]
 and updated methods using the 2D array of pixels accordingly
(to maintain continuity with how java represents images)

-added transform and filter methods to ImageModel, controller support for user input
 calling these functions, and corresponding Command and Function objects to 
 controller and model respectively
(necessary part of assignment)

A4 stuff:
OVERVIEW OF CLASSES/INTERFACES:
-----------------------------------
controller stuff:

IMEController: controller interface, has controller methods
controller: implements IMEController, controls behavior of and changes stuff in model

IMECommand: command object interface, has methods for doing command things
--7 implementations of this which do what their name suggests
-----------------------------------
model stuff:

IMEModel: model interface, has getters, setters, and methods which make new images according
          to how the controller tells it to do so
ImageModel: implements IMEModel, keeps track of images, creates new images
            using existing images as a starting point when told to do so by the controller

IMEImage: interface representing an Image, has getters and setters
Image: implements Image, keeps track of a 2D array of Pixel

IMEPixel: interface representing a Pixel, has getters and setters
Pixel: implements IMEPixel, keeps track of RGBA values

IMEViewModel: model interface with limited functionality for future view functionality
ViewModel: implements IMEViewModel

--Function<IMEPixel, IMEPixel> and Function<IMEPixel[][], IMEPixel[][]> objects for
  each command the user can enter which control the model-side logic for them
------------------------------------
view stuff:

IMEView: view interface, can render a message
TextView: implements IMEView, renders messages as text to input Appendable
------------------------------------


REDWOOD IMAGE CITATION:
https://cdn.mos.cms.futurecdn.net/kqHxHZDdtV6wj6r9Zdg8hK-1024-80.jpg.webp