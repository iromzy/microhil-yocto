# Microhil Yocto Project

This document explains how to clone and set up your Yocto environment with `repo`, then build an image for Raspberry Pi 5.

---

## **1. Prerequisites**
- **Operating System**: Linux (Ubuntu/Debian recommended).
- **Required Packages**:
  ```bash
  sudo apt update && sudo apt install -y git repo python3 python3-pip gawk wget diffstat unzip texinfo gcc-multilib build-essential chrpath socat libsdl1.2-dev xterm
  ```
- **Disk Space**: At least 50 GB free.
- **Internet Connection**: Required to clone layers and download sources.

---

## **2. Clone the Repository with `repo`**

1. **Initialize `repo`**:
   ```bash
   repo init -u <repository-git-url> -b <branch> --no-clone-bundle
   ```
   Example:
   ```bash
   repo init -u https://github.com/iromzy/microhil-yocto.git -b main --no-clone-bundle
   ```

2. **Sync Submodules**:
   ```bash
   repo sync
   ```

3. **Layer Structure**:
   After syncing, you will have a directory structure with the required layers, for example:
   ```
   microhil-yocto/
   ├── default.xml
   ├── build/
   ├── poky/
   ├── meta-raspberrypi/
   ├── meta-openembedded/
   ├── meta-microhil/
   └── ...
   ```

---

## **3. Configure the Build Environment**

1. **Set up the environment with `oe-init-build-env`**:
   ```bash
   source poky/oe-init-build-env build
   ```
   This command places you in the `build/` directory.

2. **Edit `bblayers.conf`**:
   Ensure all required layers are included in `conf/bblayers.conf`, for example:
   ```bash
   BBLAYERS ?= " \
       ${TOPDIR}/../poky/meta \
       ${TOPDIR}/../poky/meta-poky \
       ${TOPDIR}/../meta-raspberrypi \
       ${TOPDIR}/../meta-openembedded/meta-networking \
       ${TOPDIR}/../meta-openembedded/meta-python \
       ${TOPDIR}/../meta-microhil \
   "
   ```

3. **Edit `local.conf`**:
   Update the build configuration in `conf/local.conf`:
   - **Target Machine**:
     ```bash
     MACHINE = "raspberrypi5"
     ```
   - **Build Parallelization**:
     ```bash
     BB_NUMBER_THREADS = "$(nproc)"
     PARALLEL_MAKE = "-j$(nproc)"
     ```

---

## **4. Build the Image**

1. **Start the Build**:
   ```bash
   bitbake microhil-image
   ```

2. **Check the Generated File**:
   After the build completes, the image will be available in:
   ```bash
   build/tmp/deploy/images/raspberrypi5/
   ```

   Example generated file:
   ```
   core-image-microhil-raspberrypi5.wic.gz
   ```

---

## **5. Flash the Image to an SD Card**

1. **Decompress the Image**:
   ```bash
   gunzip core-image-microhil-raspberrypi5.wic.gz
   ```

2. **Flash the SD Card**:
   ```bash
   sudo dd if=core-image-microhil-raspberrypi5.wic of=/dev/sdX bs=4M status=progress conv=fsync
   ```
   Replace `/dev/sdX` with the correct path to your SD card (use `lsblk` to check).

3. **Insert the SD Card** into your Raspberry Pi and boot it.

---

## **6. Access the Raspberry Pi**

1. **Connect via Ethernet (mDNS)**:
   ```bash
   ssh microhil@raspberrypi.local
   ```
   Default password: `changeme` (adjust in configuration if needed).

2. **Connect via Wi-Fi**: Configure the `wpa_supplicant.conf` file if needed to join a Wi-Fi network.

---
