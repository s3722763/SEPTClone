resource "aws_key_pair" "deployer" {
  key_name   = "deployer-key"
  public_key = var.public_key
}

resource "aws_launch_configuration" "project" {
  name            = "web_config"
  image_id        = var.ami_id
  instance_type   = "t2.micro"
  security_groups = [aws_security_group.allow_http_ssh.id]

  key_name = aws_key_pair.deployer.key_name
}

resource "aws_lb_target_group" "project" {
  name     = "todo-app-target-group"
  port     = 80
  protocol = "HTTP"
  vpc_id   = aws_vpc.main.id
}

resource "aws_autoscaling_group" "project" {
  name                 = "terraform-asg-example"
  launch_configuration = aws_launch_configuration.project.name
  min_size             = 1
  max_size             = 1
  vpc_zone_identifier  = [aws_subnet.private_az1.id, aws_subnet.private_az2.id, aws_subnet.private_az3.id]
  target_group_arns    = [aws_lb_target_group.project.arn]
}

resource "aws_lb" "project" {
  name               = "project-lb"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.allow_http_ssh.id]
  subnets            = [aws_subnet.public_az1.id, aws_subnet.public_az2.id, aws_subnet.public_az3.id]

  tags = {
    Environment = "production"
  }
}

resource "aws_lb_listener" "front_end" {
  load_balancer_arn = aws_lb.project.arn
  port              = "80"
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.project.arn
  }
}

resource "aws_db_instance" "database" {
  engine                 = "mariadb"
  engine_version         = "10.4.8"
  allocated_storage      = 20
  instance_class         = "db.t2.micro"
  username               = "root"
  password               = "root"
  name                   = "project"
  storage_type           = "gp2"
  vpc_security_group_ids = [aws_security_group.database_sg.id]
  publicly_accessible    = false
  db_subnet_group_name   = aws_db_subnet_group.db_subnet_group.name
  port                   = 3306
}
