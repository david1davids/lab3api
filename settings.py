MONGO_DBNAME = 'tasksdb'
MONGO_HOST = '159.89.5.4'
MONGO_PORT = ''

RESOURCE_METHODS = ['GET', 'POST', 'DELETE']
ITEM_METHODS = ['GET', 'PATCH', 'PUT', 'DELETE']
VALIDATION_ERROR_STATUS = 400

X_DOMAINS = ['http://127.0.0.1:8000',
             'http://editor.swagger.io',
             'http://petstore.swagger.io']

DOMAIN = {
'user': {
        'schema': {
            'firstname': {
                'type': 'string',
                'maxlength': 30
            },
            'lastname': {
                'type': 'string',
                'maxlength': 30
            },
            'phone': {
                'type': 'string'
            },
            'location': {
                'type': 'dict',
                'schema': {
                    'address': {'type': 'string'},
                    'city': {'type': 'string'}
                },
            },
        }
    },
    'tasks':{
        'schema':{
            'taskname': {
                'type': 'string',
                'minlength': 2,
                'required': True
            },
            'completed': {
                'type': 'boolean'
            },
            'assigned-to': {
                'type': 'list',
                'data_relation': {
                    'field': '_id',
                    'resource': 'user',
                    'embeddable': True
                }
            }
        }
    }
}
