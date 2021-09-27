# ubirch-test-webserver

[![App Status](https://ci.dev.ubirch.com/api/badge?name=ubirch-test-webserver&revision=true)](https://ci.dev.ubirch.com/applications/ubirch-test-webserver)

![example workflow](https://github.com/behoof4mind/ubirch-test-webserver/actions/workflows/build.yml/badge.svg)

### TODO

Here the list of things that need to be done to use this project as a template/example per organization:
- Create technical GitHub admin user that will be able to push changes to the protected main branch
- Generate token for this user and put it to the GitHub secrets
- Use this secret in [build.yml](.github/workflows/build.yml?plain=1#L90-L92) file instead of `TOKEN_DENIS`
  ```yaml
    steps:
      - uses: actions/checkout@v2
        with:
          token: ${{ secrets.TOKEN_DENIS }}
  ```
- Replace docker.io registry credentials in [build.yml](.github/workflows/build.yml?plain=1#L99-L104) and [build-unstable.yml](.github/workflows/build-unstable.yml?plain=1#L69-L74) files:
  ```yaml
        - name: Login to DockerHub
          uses: docker/login-action@v1
          with:
            registry: docker.io
            username: behoof4mind
            password: ${{ secrets.DOCKERHUB_TOKEN }}
  ```
- Change docker and Java libraries repositories in [pom.xml](pom.xml?plain=1#L445):
```xml
...
  <configuration>
      <buildDirectory>${project.build.outputDirectory}</buildDirectory>
      <repository>docker.io/behoof4mind/ubirch-test-web-server</repository>
      <tag>${project.version}</tag>
      <buildArgs>
          <VERSION>${project.version}</VERSION>
          <BUILD>${build.number}</BUILD>
...
```
- Change Maintainer in [Dockerfile](Dockerfile?plain=1#L2)
- Create based on this or in another project templates for GitHub actions on organizational level [_Sharing workflows with your organization_](https://docs.github.com/en/actions/learn-github-actions/sharing-workflows-with-your-organization)
