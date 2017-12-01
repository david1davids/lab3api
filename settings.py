MONGO_DBNAME = 'lab3db'

RESOURCE_METHODS = ['GET', 'POST', 'DELETE']
ITEM_METHODS = ['GET', 'PATCH', 'PUT', 'DELETE']
VALIDATION_ERROR_STATUS = 400

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
