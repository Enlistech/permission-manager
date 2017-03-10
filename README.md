# PermissionManager

[![](https://jitpack.io/v/TheBrownArrow/PermissionManager.svg)](https://jitpack.io/#TheBrownArrow/PermissionManager)

Permission Manager is easily manage Android Marshmallow and nougat runtime permissions.

This library is backwards compatible. In pre-Marshmallow devices permissions are returned as given. This is done using the Android Support library `AppCompatActivity` and support `Fragment` methods for permissions.

### Requirements

The library requires Android API Level 9+.


# Installation

### To get a Git project into your build:

**Step 1.** Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
**Step 2.** Add the dependency
```java
dependencies {
	        compile 'com.github.TheBrownArrow:PermissionManager:1.0.0'
	}
```

# How to Use this library

### Inside Activity

**Step 1.** Extends `ActivityManagePermission` in your Activity.
```java
public class MainActivity extends ActivityManagePermission {

}
```
**Step 2.** Example usage to ask camera permission.
```java
askCompactPermission(PermissionUtils.Manifest_CAMERA, new PermissionResult() {
                    @Override
                    public void permissionGranted() {
                        //permission granted
                        //replace with your action
                    }

                    @Override
                    public void permissionDenied() {
                        //permission denied
                        //replace with your action
                    }
                       @Override
                    public void permissionForeverDenied() {
                     // user has check never ask again
                     // you need to open setting manually
                     openSettingsApp(MainActivity.this);
                    }
                });
```

### Inside Fragment

**Step 1.** Extends `FragmentManagePermission` in your Fragment.
```java
public class MainFragment extends FragmentManagePermission {

}
```
**Step 2.** Example usage to ask camera permission.
```java
askCompactPermission(PermissionUtils.Manifest_CAMERA, new PermissionResult() {
                    @Override
                    public void permissionGranted() {
                        //permission granted
                        //replace with your action
                    }

                    @Override
                    public void permissionDenied() {
                        //permission denied
                        //replace with your action
                    }
                       @Override
                    public void permissionForeverDenied() {
                     // user has check never ask again
                     // you need to open setting manually
                     openSettingsApp(MainActivity.this);
                    }
                });
```


# Advance Usage

### Check whether a permission has been granted.
```java
boolean isPermissionGranted = isPermissionGranted(MainActivity.this, PermissionUtils.Manifest_WRITE_EXTERNAL_STORAGE);
```
### Check whether a multiple permission has been granted.
```java
boolean isPermissionGranted = isPermissionsGranted(MainActivity.this, new String[] {PermissionUtils.Manifest_WRITE_EXTERNAL_STORAGE, PermissionUtils.Manifest_CAMERA});
```

### Open Application Setting
Need to class extend `FragmentManagePermission` for `Fragment` and `ActivityManagePermission` for `Activity`
```java
openSettingsApp(MainActivity.this);
```

## Multiple Permission
```java
askCompactPermissions(new String[]{PermissionUtils.Manifest_CAMERA, PermissionUtils.Manifest_WRITE_EXTERNAL_STORAGE}, new PermissionResult() {
            @Override
            public void permissionGranted() {
                //permission granted
                //replace with your action
            }

            @Override
            public void permissionDenied() {
                //permission denied
                //replace with your action
            }
             @Override
                    public void permissionForeverDenied() {
                     // user has check 'never ask again'
                     // you need to open setting manually
                     openSettingsApp(MainActivity.this);
                    }
        });
```

## Group Permissions
```java
askCompactPermission(PermissionUtils.Manifest_GROUP_STORAGE, PermissionUtils.Manifest_WRITE_EXTERNAL_STORAGE}, new PermissionResult() {
            @Override
            public void permissionGranted() {
                //permission granted
                //replace with your action
            }

            @Override
            public void permissionDenied() {
                //permission denied
                //replace with your action
            }
             @Override
                    public void permissionForeverDenied() {
                     // user has check 'never ask again'
                     // you need to open setting manually
                     openSettingsApp(MainActivity.this);
                    }
        });
```


# Let us know
We’d be really happy if you sent us links to your projects where you use our component. Just send an email to <a href="mailto:storedeveloper17@gmail.com">storedeveloper17@gmail.com</a> And do let us know if you have any questions or suggestion regarding the Permission Manager.


# License

```
Copyright © 2017 by The Brown Arrow

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
